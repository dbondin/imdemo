package ru.stm.imdemo.server.controller;

    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import ru.stm.imdemo.server.domain.Message;
    import ru.stm.imdemo.server.repos.MessageRepo;

@RestController
        @RequestMapping("/")
        public class MainController {
            private MessageRepo messageRepo;

        @Autowired
        public MainController(MessageRepo messageRepo){
            this.messageRepo = messageRepo;
            }
        @GetMapping
       public Iterable<Message> list(){
            return messageRepo.findAll();
        }

        @GetMapping({"id"})
         public Message getOneById(@PathVariable("id") Message message){
            return message;
        }

        @PostMapping
        public Message create(@RequestBody Message message){
            return messageRepo.save(message);
        }

        @PutMapping({"id"})
        public Message update(@PathVariable("id") Message messageFromDb,
                              @RequestBody Message message){
            BeanUtils.copyProperties(message, messageFromDb, "id");
            return messageRepo.save(messageFromDb);
        }

        @DeleteMapping({"id"})
        public void delete(@PathVariable("id") Message message){
            messageRepo.delete(message);
        }

    }
//
//    //Приветствие
//    @GetMapping("/")
//    public String greeting(Map<String, Object> model) {
//        return "greeting";
//    }

    /*Возвращает главную страницу и все сообщения на ней
      Так же здесь реализован метод "filter" т.е. поиск по тегу
     */
//    @GetMapping("/")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
//        Iterable<Message> messages = messageRepo.findAll();
//FIXME
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepo.findByTag(filter);
//        } else {
//            messages = messageRepo.findAll();
//        }
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//        return "/";
//    }

    //Метод добавления сообщений
//    @PostMapping("/")
//    public String add(  @AuthenticationPrincipal User user,
//                        @RequestParam String text,
//                        @RequestParam String tag, Map<String, Object> model){
//        Message message = new Message();
//        message.setFromUser(user);
//        message.setText(text);
//        //FIXME message.setToUser(user);
//        messageRepo.save(message);
//        Iterable<Message> messages = messageRepo.findAll();
//        model.put("messages", messages);
//        return "/";
//    }
//}

