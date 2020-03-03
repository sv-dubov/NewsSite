package dto;

public class CategoryDTO {
	
	private long id;
    private String name;
    private String urlSlug;
    private String description;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(long id, String name, String urlSlug, String description) {
		super();
		this.id = id;
		this.name = name;
		this.urlSlug = urlSlug;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", urlSlug=" + urlSlug + ", description=" + description
				+ "]";
	}
}