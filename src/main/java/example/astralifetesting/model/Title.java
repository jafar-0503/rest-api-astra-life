package example.astralifetesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import example.astralifetesting.auditable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "titles")
public class Title extends Auditable<String> {

    @Column(name = "title", length = 50)
    private String title;
}
