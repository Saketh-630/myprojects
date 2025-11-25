package com.demo.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="MyUser")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
private String name;
private String email;


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "profile_id",referencedColumnName = "id")
	private Profile profile;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@ManyToMany
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;
	
	
}
