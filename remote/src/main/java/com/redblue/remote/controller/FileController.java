package com.redblue.remote.controller;

import com.redblue.remote.service.FileService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
    private final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String viewFileForm() {
        LOGGER.info("viewFileForm 호출!!");
        return "fileForm";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        LOGGER.info("uploadFile 호출!!");
        StringBuilder messages = new StringBuilder();

        fileService.uploadFile(file);
        messages
                .append("You successfully upload!")
                .append("\n")
                .append(file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("message",
                messages);

        return "redirect:/";
    }

    @PostMapping("/file/one")
    public String onePickUploadedFile(@RequestParam("filename") String filename, Model model) {
        LOGGER.info("onePickUploadedFile 호출!!");

        model.addAttribute("fileOne",
                fileService.loadOne(filename).map(path ->
                                MvcUriComponentsBuilder.fromMethodName(
                                                FileController.class,
                                                "serveFile",
                                                path.getFileName().toString())
                                        .build().toUri().toString())
                        .collect(Collectors.joining()));

        return "fileForm";
    }

    @PostMapping("/file/list")
    public String listUploadedFile(Model model) {
        LOGGER.info("listUploadedFile 호출!!");

        model.addAttribute("files",
                fileService.loadAll().map(path ->
                                MvcUriComponentsBuilder.fromMethodName(
                                                FileController.class,
                                                "serveFile",
                                                path.getFileName().toString())
                                        .build().toUri().toString())
                        .collect(Collectors.toList()));

        return "fileForm";
    }

    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        LOGGER.info("serveFile 호출!!");

        Resource file = fileService.loadAsResource(filename);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        file.getFilename() + "\"")
                .body(file);
    }
}
