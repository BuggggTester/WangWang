package com.example.demo.entity.hotel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Slf4j
@Component
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String picturePath;
    private String description;
    private String score;

    //一个酒店对应多个房间，
    //cascade = CascadeType.ALL: 表示对该关联关系的操作会被级联到关联的实体。
    // 例如，如果删除了一个酒店，那么与之关联的所有房间也会被删除。
    //orphanRemoval = true: 表示当从关联关系中移除了一个房间实体时（例如从酒店的房间列表中移除了一个房间），
    // 这个房间实体会被自动删除。这保证了数据库中不会残留无关联的房间数据。
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

}
