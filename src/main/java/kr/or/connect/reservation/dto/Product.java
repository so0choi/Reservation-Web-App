package kr.or.connect.reservation.dto;

public class Product {
	private Integer displayInfoId;
	private String placeName;
	private String content;
	private String description;
	private Integer id;
	private String productImageUrl;

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Product [displayInfoId=" + displayInfoId + ", placeName=" + placeName + ", content=" + content
				+ ", description=" + description + ", id=" + id + ", productImageUrl=" + productImageUrl + "]";
	}

}
