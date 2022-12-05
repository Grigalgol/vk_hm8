package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class Company {
    @JsonProperty("id")
    private @NotNull Integer id;
    @JsonProperty("name")
    private @NotNull String name;
}
