package kalita.projects.spring.service;

import kalita.projects.spring.domain.Message;
import kalita.projects.spring.domain.User;
import kalita.projects.spring.domain.dto.MessageDTO;
import kalita.projects.spring.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class MessageService {

    private final MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Page<MessageDTO> messageList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable, user);
        } else {
            return messageRepo.findAll(pageable, user);
        }
    }

    public Page<MessageDTO> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, currentUser, author);
    }

    public void save(Message message) {
        messageRepo.save(message);
    }

    public Iterable<Message> findAll() {
        return messageRepo.findAll();
    }

    public void updateMessage(
            User currentUser,
            Message message,
            String tag,
            String text,
            MultipartFile file
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }
            saveFile(message, file);
            messageRepo.save(message);
        }
    }

    public void saveFile(
            @Valid Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);
            boolean mkdir = true;
            if (!uploadFolder.exists()) {
                mkdir = uploadFolder.mkdir();
            }
            if (mkdir) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                message.setFilename(resultFileName);
            }
        }
    }
}
