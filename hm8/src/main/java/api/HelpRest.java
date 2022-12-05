package api;

import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("/help")
@NoArgsConstructor
public final class HelpRest {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getDescription() {
        return HelpRest.class.getResourceAsStream("/static/help.html");
    }
}
