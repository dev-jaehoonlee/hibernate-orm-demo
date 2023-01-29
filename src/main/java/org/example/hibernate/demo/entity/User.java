package org.example.hibernate.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.text.MessageFormat.format;
import static java.util.Objects.hash;
import static java.util.stream.Collectors.joining;

@NamedEntityGraph(
        name = "user-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "details"),
                @NamedAttributeNode(value = "skills")
        }
)
@Table(name = "users")
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private Set<UserDetail> details;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private Set<UserSkill> skills = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<UserDetail> details) {
        this.details = details;
    }

    public Set<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<UserSkill> skills) {
        this.skills = skills;
    }

    public UserDetail getDetail() {
        return details.stream().findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    @Override
    public String toString() {
        return format("User(id={0}, name={1}, detail={2}, skills=[{3}])",
                id, name, getDetail(), skills.stream().map(UserSkill::toString).collect(joining(", ")));
    }
}
