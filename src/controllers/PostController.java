package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.post.AddPostDTO;
import dto.ListItemDTO;
import dto.PostDTO;

@Controller
public class PostController {
	@RequestMapping("/addPost")
	public String index(Model model) {
		AddPostDTO addModel = new AddPostDTO();
		
		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
		categories.add(new ListItemDTO("Плодові", "1"));
		categories.add(new ListItemDTO("Неплодові", "2"));
		
		addModel.setCategories(categories);
		model.addAttribute("add", addModel);
		return "addPost";
	}
	
	@RequestMapping("/post")
	public String post(Model model) {
		PostDTO addModel = new PostDTO();
		model.addAttribute("posts", addModel);
		return "post";
	}
}
