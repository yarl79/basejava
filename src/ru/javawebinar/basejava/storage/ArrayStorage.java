package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.Objects;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++)
            storage[i] = null;
        size = 0;
    }


    public void save(Resume r) {
        storage[size++] = Objects.requireNonNull(r);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        return (index == -1) ? null : storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) return;

        for (int i = index; i < size - 1; i++)
            storage[i] = storage[i + 1];

        storage[--size] = null;
    }

    private int getIndex(String uuid) {
        if (uuid == null) return -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }


    public void update(Resume resume) {
        int index = getIndex(Objects.requireNonNull(resume.uuid));
        if (index != -1) {
            storage[index] = resume;
        }
    }
}
