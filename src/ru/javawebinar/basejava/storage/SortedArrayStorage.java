package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int getIndex(String uuid) {
        Resume r = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, r);
    }

    @Override
    protected void saveResume(Resume r, int index) {
        int insertedIndex = -index - 1;
        System.arraycopy(storage, insertedIndex, storage, insertedIndex + 1, size - insertedIndex);
        storage[insertedIndex] = r;
    }

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

}
