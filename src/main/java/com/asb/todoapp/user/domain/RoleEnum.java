package com.asb.todoapp.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

   ADMIN(Set.of(PermissionEnum.WRITE_TODO, PermissionEnum.READ_TODO)),
   USER(Set.of(PermissionEnum.READ_TODO));

   private final Set<PermissionEnum> permissions;

}
