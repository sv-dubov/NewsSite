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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.CategoryDTO;
import dto.ListItemDTO;
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
		return new ModelAndView("category", "cat", catDTO);
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
	
	@RequestMapping(value = "/editCat/{id}", method = RequestMethod.GET)
	public String showEditCatForm(@PathVariable("id") String id, WebRequest request, Model model) {
		CategoryDTO catDTO = new CategoryDTO();
		Category category = catService.GetById(Long.parseLong(id));
		catDTO.setId(category.getId());
		catDTO.setName(category.getName());
		catDTO.setDescription(category.getDescription());
		catDTO.setUrlSlug(category.getUrlSlug());
		model.addAttribute("catEdit", catDTO);
		return "editCat";
	}

	@RequestMapping(value = "/editCat/{id}", method = RequestMethod.POST)
	public String editCat(@PathVariable("id") String id, @ModelAttribute("catDTO") CategoryDTO catDTO, 
		      BindingResult result, ModelMap model) {
		System.out.println("id edit" + id);
		catDTO.setId(Long.valueOf(id));
		System.out.println(catDTO.toString());
		catService.Update(catDTO);
		model.addAttribute("categories", catService.GetAll());
		return "category";
	}
	
	@RequestMapping(value = "/delCat/{id}", method = RequestMethod.GET)
	public String showDeleteCatForm(@PathVariable("id") String id, WebRequest request, Model model) {
		CategoryDTO catDTO = new CategoryDTO();
		Category category = catService.GetById(Long.parseLong(id));
		catDTO.setId(category.getId());
		catDTO.setName(category.getName());
		catDTO.setDescription(category.getDescription());
		catDTO.setUrlSlug(category.getUrlSlug());
		model.addAttribute("catDel", catDTO);
		return "delCat";
	}

	@RequestMapping(value = "/delCat/{id}", method = RequestMethod.POST)
	public String deleteCat(@PathVariable("id") String id, @ModelAttribute("catDTO") CategoryDTO catDTO,
			BindingResult result, ModelMap model) {
		System.out.println("id" + id);
		long deleteId = Long.parseLong(id);
		catService.Delete(deleteId);
		model.addAttribute("categories", catService.GetAll());
		return "category";
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

}