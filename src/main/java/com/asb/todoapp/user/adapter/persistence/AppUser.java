package com.asb.todoapp.user.adapter.persistence;

import com.asb.todoapp.user.domain.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

   @Id
   private String id;

   private String username;

   private String password;

   private RoleEnum role;

   public List<SimpleGrantedAuthority> getAuthorities() {
      return role.getPermissions().stream()
          .map(a -> new SimpleGrantedAuthority(a.name())).toList();
   }

   public AppUser(String username, String password) {
      this.username = username;
      this.password = password;
      this.role = RoleEnum.ADMIN;
   }

}
