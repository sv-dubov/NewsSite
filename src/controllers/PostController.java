package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.post.AddPostDTO;
import entities.Category;
import entities.Post;
import services.CategoryService;
import services.PostService;
import dto.CategoryDTO;
import dto.ListItemDTO;
import dto.PostDTO;

@Controller
public class PostController {
//	@RequestMapping("/addPost")
//	public String index(Model model) {
//		AddPostDTO addModel = new AddPostDTO();
//		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
//		categories.add(new ListItemDTO("Плодові", "1"));
//		categories.add(new ListItemDTO("Неплодові", "2"));
//		addModel.setCategories(categories);
//		model.addAttribute("add", addModel);
//		return "addPost";
//	}
//	
//	@RequestMapping("/post")
//	public String post(Model model) {
//		PostDTO addModel = new PostDTO();
//		model.addAttribute("posts", addModel);
//		return "post";
//	}
	
	@Autowired
	private PostService postService;
	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String showAddPostForm(WebRequest request, Model model) {
		PostDTO postDTO = new PostDTO();
	    model.addAttribute("posts", postDTO);
	    return "addPost";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public ModelAndView addPost(@ModelAttribute("posts") PostDTO postDTO, WebRequest request) {
		Post post = new Post();
		System.out.println(postDTO.toString());
		post = addNewPost(postDTO);
		return new ModelAndView("addPost", "posts", postDTO);
	}
	
	private Post addNewPost(PostDTO postDTO) {
		Post post = null;
	    try {
	        post = postService.Add(postDTO);
	    } catch (Exception e) {
	        return null;
	    }    
	    return post;
	}
}
