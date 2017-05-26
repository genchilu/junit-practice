package url.genchi.practice.db;

import java.util.UUID;

/**
 * Created by genchi.lu on 2017/5/26.
 */
public class TodoList {
  final private UUID id;
  private String description;

  public TodoList(String description) {
    this.id = UUID.randomUUID();
    this.description = description;
  }

  public TodoList(UUID id, String description) {
    this.id = id;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  public boolean equals(TodoList tdl) {
    return this.id.equals(tdl.id) && this.description.equals(tdl.description);
  }
}
