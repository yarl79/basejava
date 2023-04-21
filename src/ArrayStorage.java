import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < getSize(); i++)
            storage[i] = null;
    }


    void save(Resume r) {
        storage[getSize()] = Objects.requireNonNull(r);
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < getSize(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    void delete(String uuid) {
        if (uuid == null) return;

        Resume deleted = get(uuid);
        if (deleted == null) return;

        int index = Arrays.asList(storage).indexOf(deleted);
        if (index == -1) return;
        int size = getSize();
        for (int i = index; i < size - 1; i++)
            storage[i] = storage[i + 1];

        storage[size - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, getSize());
    }

    int size() {
        return getSize();
    }

    private int getSize() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                size = i;
                break;
            }
        }
        return size;
    }
}
