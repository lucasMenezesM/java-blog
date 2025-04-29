package com.iff.NexusBlog.models;

import jakarta.persistence.*;

// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class,
//   property = "id"
// )
@Entity
@Table(name = "common_users")
public class CommonUser extends User {
  
}
