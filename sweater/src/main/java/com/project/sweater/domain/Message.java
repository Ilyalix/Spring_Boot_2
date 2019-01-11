package com.project.sweater.domain;

import javax.persistence.*;

@Entity // сущность(объект), которую необходимо сохратять в базе данных
// Entity говорит что нам надо сделать таблицу из этого класса!
@Table(name = "messages")
public class Message {
    @Id  // идентификатор
    @GeneratedValue(strategy=GenerationType.AUTO) // автоматич. idea в нужном порядке загрузит

    private Integer id;
    private String text;
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER) // одному пользователю соответств. множесто сообщений.
                                        // EAGER - получение каждый раз инфо об авторе
    @JoinColumn(name = "user_name")
    private User author;

    public Message (){
    }

    public Message (String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author = user;
    }

    // проверка есть ли у нас везде автор
    public String getAuthorName(){
        return author !=null ? author.getUsername() : "<none>"; // если автор не равен нулю(имеется), возвращаем Username, в противном случаее "none"
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
}

