package com.depahelix.colorvote;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class VoteController {

  private String _favoriteColor;
  private static VoteRepository _voteRepository;
  private static ArrayList<String> _validColors;
  private final static String COOKIE_NAME = "color-vote-cookie";
  private static VoteController _instance;

  public static VoteController getInstance(VoteRepository voteRepository) {
    if (_instance == null) {
      _instance = new VoteController();
      _instance.setVoteRepository(voteRepository);
    }
    return _instance;
  }

  private VoteController() {
    String[] vc = {"white", "silver", "gray", "black", "red", "maroon", "yellow",
        "olive", "lime", "green", "aqua", "teal", "blue", "navy", "fuchsia", "purple"};
    _validColors = new ArrayList<>();
    for (String color : vc) _validColors.add(color);
  }

  @GetMapping("/vote")
  public String greeting(@RequestParam(name="favoriteColor", required=false, defaultValue="") String favoriteColorInput,
                         Model model,
                         HttpServletRequest request,
                         HttpServletResponse response) {
    String favoriteColor = favoriteColorInput.replaceAll("[^a-zA-Z]", "").toLowerCase();

    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(COOKIE_NAME)) {
          String originalVote = cookie.getValue();
          model.addAttribute("favoriteColor", originalVote);
          if (originalVote.equals(favoriteColor)) {
            model.addAllAttributes(mapVotes());
            return "thanks";
          } else {
            return "alreadyVoted";
          }
        }
      }
    }

    model.addAttribute("favoriteColor", favoriteColor);

    if (_validColors.contains(favoriteColor)) {
      Cookie myCookie =
          new Cookie(COOKIE_NAME, favoriteColor);
      response.addCookie(myCookie);
      saveVote(favoriteColor);
      model.addAllAttributes(mapVotes());
      return "thanks";
    } else {
      return "nope";
    }
  }

  private HashMap<String, Double> mapVotes() {
    Double totalcount = 0D;
    HashMap<String, Double> mapCounts = new HashMap<>();
    for (Vote vote : _voteRepository.findAll()) {
      totalcount++;
      String favoriteColor = vote.getFavoritecolor();
      if (favoriteColor != null) {
        Double favCount = mapCounts.get(favoriteColor);
        if (favCount == null) favCount = 0D;
        mapCounts.put(vote.getFavoritecolor(), favCount + 1D);
      }
    }
    HashMap<String, Double> finalMap = new HashMap<>();
    for (String color : _validColors) {
      Double favCount = mapCounts.get(color);
      if (favCount != null) finalMap.put(color, (favCount / totalcount) * 100D);
      else finalMap.put(color, 0D);
    }
    return finalMap;
  }

  public void setVoteRepository(VoteRepository voteRepository) {
    _voteRepository = voteRepository;
  }

  private void saveVote(String favoriteColor) {
    UUID uuid = UUID.randomUUID();
    _voteRepository.save(new Vote("?", uuid, favoriteColor));
  }
}