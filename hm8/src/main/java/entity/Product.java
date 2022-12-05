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

    @JsonProperty("companyId")
    private @NotNull Integer companyId;

    @JsonProperty("count")
    private @NotNull Integer count;

    @JsonCreator
    public Product(@JsonProperty(value = "name", required = true) String name,
                   @JsonProperty(value = "companyId", required = true) Integer companyId,
                   @JsonProperty(value = "count", required = true) Integer count){
        this.name = name;
        this.companyId = companyId;
        this.count = count;
    }
}
