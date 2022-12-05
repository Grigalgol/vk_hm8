package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class Product {
    @JsonProperty("id")
    private @NotNull Integer id;

    @JsonProperty("name")
    private @NotNull String name;

    @JsonProperty("companyName")
    private @NotNull String companyName;

    @JsonProperty("count")
    private @NotNull Integer count;

    @JsonCreator
    public Product(@JsonProperty(value = "name", required = true) String name,
                   @JsonProperty(value = "factoryName", required = true) String companyName,
                   @JsonProperty(value = "count", required = true) Integer count){
        this.name = name;
        this.companyName = companyName;
        this.count = count;
    }
}
