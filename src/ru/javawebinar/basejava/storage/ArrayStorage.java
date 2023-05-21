package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Objects.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void concreteSave(Resume r, int index) {
            storage[size++] = requireNonNull(r);
    }

    @Override
    protected void concreteDelete(int index) {
        storage[index] = storage[--size];
        storage[size] = null;
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
