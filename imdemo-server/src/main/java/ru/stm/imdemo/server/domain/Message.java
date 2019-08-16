package ru.stm.imdemo.server.domain;
/**
 * Создаем message: сущность в БД (Показываем это с помощью аннотации @Entity)
 * Создаем поля у этой сущности (id, text, tag, author). С помощью аннотаций @Id, показываем, что поле id идентификатор (primary key)
 * аннотация @GeneratedValue(...), автоматически генерирует значение id
 * Так как поля имеют можификатор доступа private создаем геттеры и сеттеры для каждого поля класса
 */
import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    /*
        Поле author имеет аннотации @ManyToOne(показывает,
        что одному пользователю
        могут соответствовать несколько сообщений)
        указали fetch EAGER, т.е. мы хотим получать
        сразу информацию, кто автор сообщения
        @JoinColumn создает колонку "user_id"
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    //Конструктор по-умолчанию
    public Message(){

    }

    //Получаем имя автора, если имя автора не указано, то возвращает <none>
    public String getAuthorName(){
        return author != null? author.getUsername() : "<none>";
    }

    //Конструктор с параметрами
    public Message(String text, String tag, User user){
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public User getAuthor(){
        return author;
    }

    public void setAuthor(User author){
        this.author = author;
    }
}
