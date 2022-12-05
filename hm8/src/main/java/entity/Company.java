package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class Company {
    @JsonProperty("id")
    private @NotNull Integer id;
    @JsonProperty("name")
    private @NotNull String name;
}
