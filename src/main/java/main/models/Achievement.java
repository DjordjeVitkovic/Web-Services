package main.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType.SignatureRelevant;

@Entity
@Table
public class Achievement {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@Size(max = 100, message = "Maximum numbers of characters is 100!")
	private String displayName;
	@Size(max = 500, message = "Maximum numbers of characters is 500!")
	private String description;
	private String icon;
	private int displayOrder;
	private String createDate;
	private String updateDate;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "gameId")
	@JsonIgnore
	private Game gameId;

	public Achievement() {
	}

	public Achievement(String displayName, String description, String icon, int displayOrder, String createDate,
			String updateDate, Game gameId) {
		super();
		this.displayName = displayName;
		this.description = description;
		this.icon = icon;
		this.displayOrder = displayOrder;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.gameId = gameId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Game getGameId() {
		return gameId;
	}

	public void setGameId(Game gameId) {
		this.gameId = gameId;
	}

	public void setDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		this.setCreateDate(sf.format(date));
		this.setUpdateDate(sf.format(date));

	}

	public void updateDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		this.setUpdateDate(sf.format(date));

	}

	@Override
	public String toString() {
		return "Achievement [id=" + id + ", displayName=" + displayName + ", description=" + description + ", icon="
				+ icon + ", displayOrder=" + displayOrder + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", gameId=" + gameId + "]";
	}

	

}
