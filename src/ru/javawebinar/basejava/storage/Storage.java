package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public interface Storage {
    Resume get(String uuid);

    void clear();

    void save(Resume r);

    void delete(String uuid);

    int getIndex(String uuid);

    Resume[] getAll();

    int size();

    void update(Resume resume);
}
