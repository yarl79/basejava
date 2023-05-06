package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Objects.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_CAPACITY = 10000;
    Resume[] storage = new Resume[STORAGE_CAPACITY];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }


    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Resume with uuid " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_CAPACITY) {
            System.out.println("Resume storage is overflowed");
        } else {
            storage[size++] = requireNonNull(r);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with uuid " + uuid + " not found");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with uuid " + uuid + " not found");
            return;
        }

        for (int i = index; i < size - 1; i++)
            storage[i] = storage[i + 1];

        storage[--size] = null;
    }

    private int getIndex(String uuid) {
        if (uuid == null) return -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
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
        int index = getIndex(requireNonNull(resume.getUuid()));
        if (index == -1) {
            System.out.println("Resume with uuid " + resume.getUuid() + " not found");
        }
        storage[index] = resume;
    }
}
