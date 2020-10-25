package com.example.lab1;

import java.util.Objects;
/**
 * Содержимое плейлиста
 */
public class PlaylistItem {
    private int id;
    private String name;
    /**
     * Конструктор
     *
     * @param id идентификатор
     * @param name название
     */
    public PlaylistItem(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Возвращает идентификатор
     *
     * @return идентификатор
     */
    public int getId() {
        return id;
    }
    /**
     * Возвращает имя плейлиста
     *
     * @return имя
     */
    public String getName() {
        return name;
    }
    /**
     * Устанавливает название плейлиста
     *
     * @param name название
     */
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "PlaylistItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistItem that = (PlaylistItem) o;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}