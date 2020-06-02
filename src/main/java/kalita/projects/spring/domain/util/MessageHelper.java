package kalita.projects.spring.domain.util;

import kalita.projects.spring.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<NONE>";
    }
}
