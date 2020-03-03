package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService catService;

	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String showAddPostForm(WebRequest request, Model model) {
		PostDTO postDTO = new PostDTO();
		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
		List<Category> listCat = catService.GetAll();
		for (Category c : listCat) {
			String idCat = Long.toString(c.getId());
			categories.add(new ListItemDTO(c.getName(), idCat));
		}
		postDTO.setCategories(categories);
		model.addAttribute("postDTO", postDTO);
		return "addPost";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("postDTO")PostDTO postDTO, 
		      BindingResult result, ModelMap model) {
		Post post = new Post();
		post = addNewPost(postDTO);
		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
		List<Category> listCat = catService.GetAll();
		for (Category c : listCat) {
			String idCat = Long.toString(c.getId());
			categories.add(new ListItemDTO(c.getName(), idCat));
		}
		postDTO.setCategories(categories);
		model.addAttribute("posts", postService.GetAll());
		return "post";
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
	
	@RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
	public String showEditPostForm(@PathVariable("id") String id, WebRequest request, Model model) {
		PostDTO postDTO = new PostDTO();
		Post post = postService.GetById(Long.parseLong(id));
		postDTO.setId(post.getId());
		postDTO.setTitle(post.getTitle());
		postDTO.setShortDescription(post.getShortDescription());
		postDTO.setDescription(post.getDescription());
		postDTO.setMeta(post.getMeta());
		postDTO.setUrlSlug(post.getUrlSlug());
		postDTO.setPostedOn(post.getPostedOn());
		postDTO.setPublished(post.getPublished());
		Long catId = post.getCategory().getId();
		postDTO.setCategory_id(catId.toString());
		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
		List<Category> listCat = catService.GetAll();
		for (Category c : listCat) {
			String idCat = Long.toString(c.getId());
			categories.add(new ListItemDTO(c.getName(), idCat));
		}
		postDTO.setCategories(categories);
		model.addAttribute("postEdit", postDTO);
		return "editPost";
	}

	@RequestMapping(value = "/editPost/{id}", method = RequestMethod.POST)
	public String editPost(@PathVariable("id") String id, @ModelAttribute("postDTO") PostDTO postDTO, 
		      BindingResult result, ModelMap model) {
		System.out.println("id edit" + id);
		postDTO.setId(Long.valueOf(id));
		System.out.println(postDTO.toString());
//		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
//		List<Category> listCat = catService.GetAll();
//		for (Category c : listCat) {
//			String idCat = Long.toString(c.getId());
//			categories.add(new ListItemDTO(c.getName(), idCat));
//		}
//		postDTO.setCategories(categories);
		postService.Update(postDTO);
		model.addAttribute("posts", postService.GetAll());
		return "post";
	}
	
	@RequestMapping(value = "/delPost/{id}", method = RequestMethod.GET)
	public String showDeleteProductForm(@PathVariable("id") String id, WebRequest request, Model model) {
		PostDTO postDTO = new PostDTO();
		System.out.println("delete method; id=" + id);
		Post post = postService.GetById(Long.parseLong(id));
		postDTO.setId(post.getId());
		postDTO.setTitle(post.getTitle());
		postDTO.setShortDescription(post.getShortDescription());
		postDTO.setDescription(post.getDescription());
		postDTO.setMeta(post.getMeta());
		postDTO.setUrlSlug(post.getUrlSlug());
		postDTO.setPostedOn(post.getPostedOn());
		postDTO.setPublished(post.getPublished());
		Long catId = post.getCategory().getId();
		postDTO.setCategory_id(catId.toString());
		List<ListItemDTO> categories = new ArrayList<ListItemDTO>();
		List<Category> listCat = catService.GetAll();
		for (Category c : listCat) {
			String idCat = Long.toString(c.getId());
			categories.add(new ListItemDTO(c.getName(), idCat));
		}
		postDTO.setCategories(categories);
		model.addAttribute("postDel", postDTO);
		return "delPost";
	}

	@RequestMapping(value = "/delPost/{id}", method = RequestMethod.POST)
	public String deleteProduct(@PathVariable("id") String id, @ModelAttribute("postDTO") PostDTO postDTO,
			BindingResult result, ModelMap model) {
		System.out.println("id" + id);
		long deleteId = Long.parseLong(id);
		postService.Delete(deleteId);
		model.addAttribute("posts", postService.GetAll());
		return "post";
	}
	
	@RequestMapping("/post")
	public String post(Model model) {
		List<PostDTO> posts = new ArrayList<PostDTO>();
		List<Post> listPosts = postService.GetAll();
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
		model.addAttribute("posts", posts);
		return "post";
	}
}
