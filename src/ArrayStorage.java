import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++)
            storage[i] = null;
        size = 0;
    }


    void save(Resume r) {
        storage[size++] = Objects.requireNonNull(r);
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < size; i++) {
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
        for (int i = index; i < size - 1; i++)
            storage[i] = storage[i + 1];

        storage[--size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
