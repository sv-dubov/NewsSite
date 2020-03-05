package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.PostDTO;
import entities.Post;
import services.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/home")
	public String post(Model model) {
		List<PostDTO> posts = new ArrayList<PostDTO>();
		List<Post> listPosts = homeService.GetAllHome();
		for (Post p : listPosts) {
			PostDTO pDto = new PostDTO();
			pDto.setId(p.getId());
			pDto.setTitle(p.getTitle());
			pDto.setShortDescription(p.getShortDescription());
			pDto.setDescription(p.getDescription());
			pDto.setMeta(p.getMeta());
			pDto.setUrlSlug(p.getUrlSlug());
			pDto.setPublished(p.getPublished());
			pDto.setPostedOn(p.getPostedOn());
			posts.add(pDto);
		}
		model.addAttribute("home", posts);
		return "home";
	}
}
