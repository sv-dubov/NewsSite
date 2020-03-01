package controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.CategoryDTO;
import dto.PostDTO;
import entities.Category;
import entities.Post;
import services.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@RequestMapping(value = "/addCat", method = RequestMethod.GET)
	public String showAddCatForm(WebRequest request, Model model) {
		CategoryDTO catDTO = new CategoryDTO();
	    model.addAttribute("cat", catDTO);
	    return "addCat";
	}

	@RequestMapping(value = "/addCat", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("cat") CategoryDTO catDTO, WebRequest request) {
		Category category = new Category();
		System.out.println(catDTO.toString());
		category = addNewCategory(catDTO);
		return new ModelAndView("addCat", "cat", catDTO);
	}
	
	private Category addNewCategory(CategoryDTO catDTO) {
		Category category = null;
	    try {
	        category = catService.Add(catDTO);
	    } catch (Exception e) {
	        return null;
	    }    
	    return category;
	}
	
	@RequestMapping("/category")
	public String post(Model model) {
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		List<Category> listCat = catService.GetAll();
		for (Category c : listCat) {
			CategoryDTO cDto = new CategoryDTO();
			cDto.setId(c.getId());
			cDto.setName(c.getName());
			cDto.setUrlSlug(c.getUrlSlug());
			cDto.setDescription(c.getDescription());
			categories.add(cDto);
		}
		model.addAttribute("categories", categories);
		return "category";
	}

	/*
	 * @RequestMapping(value = "/category", method = RequestMethod.GET) public
	 * ModelAndView showCategories(@ModelAttribute("cat") CategoryDTO catDTO,
	 * WebRequest request) { List<Category> category = new ArrayList<Category>();
	 * System.out.println(catDTO.toString()); category = showAllCategories(); return
	 * new ModelAndView("category", "cat", catDTO); }
	 * 
	 * private List<Category> showAllCategories() { List<Category> category = new
	 * ArrayList<Category>(); try { category = catService.GetAll(); } catch
	 * (Exception e) { return null; } return category; }
	 */
}