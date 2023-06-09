package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_CAPACITY = 10000;
    protected final Resume[] storage = new Resume[STORAGE_CAPACITY];
    protected int size;

    @Override
    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not found");
            return null;
        }
        return storage[index];
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    final public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= STORAGE_CAPACITY) {
            System.out.println("Resume storage is overflowed");
        } else if (index >= 0) {
            System.out.println("Resume with uuid " + r.getUuid() + " already exist");
        } else {
            saveResume(r, index);
            size++;
        }
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    final public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    final public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " not found");
        } else {
            deleteResume(index);
            size --;
            storage[size] = null;
        }
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    public abstract int getIndex(String uuid);
}
