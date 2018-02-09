package cn.li.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Groups entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "groups", catalog = "cosdb")

public class Groups implements java.io.Serializable ,Comparable<Groups> {

	// Fields

	private Integer gid;
	private String title;
	private String note;
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Actions> actionses = new HashSet<Actions>(0);

	

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "gid", unique = true, nullable = false)

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "note", length = 65535)

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupses")

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groups")

	public Set<Actions> getActionses() {
		return this.actionses;
	}

	public void setActionses(Set<Actions> actionses) {
		this.actionses = actionses;
	}

	@Override
	public String toString() {
		return "Groups [gid=" + gid + ", title=" + title + ", note=" + note + ", actionses=" + actionses + "]";
	}

	@Override
	public int compareTo(Groups o) {
		
		if(this.gid>o.gid){
			return 1;
		}else if(this.gid<o.gid){
			return -1;
		}
		return 0;
	}

	
    
	
}