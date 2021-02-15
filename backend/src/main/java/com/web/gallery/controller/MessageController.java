package com.web.gallery.controller;

import com.web.gallery.dto.MessageDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/message")
@Api(value = "artGallery", description = "artGallery")
public class MessageController {

    @Autowired
    private MessageService messageService;

    public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @ApiOperation(value = "사용자가 다른 사용자에게 메세지를 보낸다", response = NumberResult.class,
            notes = "sendMessage(String message_senderId, String message_receiverId, String message_title, String message_content)")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    private ResponseEntity<NumberResult> sendMessage(@RequestBody MessageDto message) {
        HttpStatus status = null;
        NumberResult ns = new NumberResult();

        try {
            messageService.sendMessage(message);

            ns.setValue("메세지가 정상적으로 발송되었습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.info(e + "");

            ns.setValue("메세지 발송에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<NumberResult>(ns, status);
    }

    @ApiOperation(value = "사용자가 받은 메세지 목록을 반환한다", response = List.class, notes = "getAllMyMessage(String user_id)")
    @RequestMapping(value = "/getAllMyReceiveMessage", method = RequestMethod.POST)
    private ResponseEntity<List<MessageDto>> getAllMyReceiveMessage(@RequestBody String user_id) {
        List<MessageDto> messages = null;
        HttpStatus status = null;

        try {
            messages = messageService.getAllMyReceiveMessage(user_id);

            if (messages != null) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<List<MessageDto>>(messages, status);
    }

    @ApiOperation(value = "사용자가 보낸 메세지 목록을 반환한다", response = List.class, notes = "getAllMyMessage(String user_id)")
    @RequestMapping(value = "/getAllMySendMessage", method = RequestMethod.POST)
    private ResponseEntity<List<MessageDto>> getAllMySendMessage(@RequestBody String user_id) {
        List<MessageDto> messages = null;
        HttpStatus status = null;

        try {
            messages = messageService.getAllMySendMessage(user_id);

            if (messages != null) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.ACCEPTED;
            }

        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<List<MessageDto>>(messages, status);
    }

    @ApiOperation(value = "메세지의 세부 내용을 가져온다", response = MessageDto.class, notes = "getMyMessage(int message_id)")
    @RequestMapping(value = "/getMyMessage", method = RequestMethod.POST)
    private ResponseEntity<MessageDto> getMyMessage(@RequestBody int message_id) {
        MessageDto message = null;
        HttpStatus status = null;

        try {
            message = messageService.getMyMessage(message_id);

            if (message != null) {
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            e.printStackTrace();

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<MessageDto>(message, status);
    }

    @ApiOperation(value = "메세지 읽음 처리", response = NumberResult.class, notes = "checkMessage(int message_id)")
    @RequestMapping(value = "/checkMessage", method = RequestMethod.POST)
    private ResponseEntity<NumberResult> checkMessage(@RequestBody int message_id) {
        HttpStatus status = null;
        NumberResult ns = new NumberResult();

        try {
            messageService.checkMessage(message_id);

            ns.setValue("메세지를 읽음처리하였습니다", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("메세지를 읽음처리에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<NumberResult>(ns, status);
    }

    //deleteMessage(message_id)
    @ApiOperation(value = "받아온 message_id를 이용해 메세지를 삭제한다.", response = NumberResult.class,
            notes = "deleteMessage(List<int message_id>")
    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    private ResponseEntity<NumberResult> deleteMessage(@RequestBody int message_id) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;

        try {

            messageService.deleteMessage(message_id);


            ns.setValue("메세지를 삭제했습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("메세지 삭제에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<NumberResult>(ns, status);
    }
}
