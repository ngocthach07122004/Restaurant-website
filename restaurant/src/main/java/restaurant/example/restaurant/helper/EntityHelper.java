package restaurant.example.restaurant.helper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class EntityHelper {


    // ONE TO ONE
    public <T> T findOrMerge_OTO(EntityManager entityManager, T entity, Class<T> entityClass, Object id, String errorMessage) {
        if (id != null) {
            // Tìm kiếm đối tượng dựa trên ID
            T existingEntity = entityManager.find(entityClass, id);
            if (existingEntity == null) {
                throw new IllegalArgumentException(errorMessage + id + " does not exist.");
            }
            return existingEntity;
        } else if (entity != null) {
            // Nếu không có ID nhưng có entity, kiểm tra và merge
            if (entityManager.contains(entity)) {
                return entity; // Nếu entity đã được quản lý, trả về trực tiếp
            } else {
                return entityManager.merge(entity); // Merge nếu entity không được quản lý
            }
        } else {
            throw new IllegalArgumentException(errorMessage + "Entity or ID must not be null.");
        }
    }



    // MANY TO ONE
    public <T> T findOrMerge_MTO(EntityManager entityManager, T entity, Class<T> entityClass, Object id, String errorMessage) {
        if (id != null) {
            T existingEntity = entityManager.find(entityClass, id);
            if (existingEntity == null) {
                throw new IllegalArgumentException(errorMessage + id + " does not exist.");
            }
            return existingEntity;
        } else {
            return entityManager.merge(entity);
        }
    }

    // MANY TO MANY
   public <T> List<T> processEntityList_MTM(
           EntityManager entityManager,
           List<T> entities,
           Class<T> entityClass,
           Function<T, Object> getIdFunction,
           String errorMessagePrefix
   ) {
       List<T> processedList = new ArrayList<>();
       for (T entity : entities) {
           if (entity != null) {
               Object id = getIdFunction.apply(entity);
               if (id != null) {
                   T existingEntity = entityManager.find(entityClass, id);
                   if (existingEntity == null) {
                       throw new IllegalArgumentException(errorMessagePrefix + id + " does not exist.");
                   }
                   processedList.add(existingEntity);
               } else {
                   T newEntity = entityManager.merge(entity);
                   processedList.add(newEntity);
               }
           }
       }
       return processedList;
   }


//public <T, ID> List<T> updateOrCreateRelatedEntities(
//        List<T> entities,
//        ID parentId,
//        Function<ID, T> findEntityFunction,
//        Consumer<T> setParentConsumer,
//        Function<T, T> saveEntityFunction
//) {
//    List<T> updatedEntities = new ArrayList<>();
//    for (T entity : entities) {
//        if (entity != null) {
//            // Kiểm tra nếu entity đã có ID hay không
//            ID entityId = extractId(entity); // Phương thức extractId cần được cài đặt tùy thuộc vào kiểu dữ liệu
//            if (entityId != null) {
//                // Tìm entity trong cơ sở dữ liệu
//                T existingEntity = findEntityFunction.apply(entityId);
//                if (existingEntity == null) {
//                    throw new IllegalArgumentException("Entity with ID " + entityId + " does not exist.");
//                }
//                // Cập nhật ngược quan hệ
//                setParentConsumer.accept(existingEntity);
//                updatedEntities.add(existingEntity);
//            } else {
//                // Tạo mới entity
//                setParentConsumer.accept(entity);
//                T newEntity = saveEntityFunction.apply(entity);
//                updatedEntities.add(newEntity);
//            }
//        }
//    }
//    return updatedEntities;
//}

    // ONE TO MANY
    public <T, ID> List<T> updateOrCreateRelatedEntities_OTM(
            List<T> entities,
            Function<T, ID> idExtractor, // Hàm lấy ID từ entity
            Function<ID, T> findEntityFunction, // Hàm tìm kiếm entity trong cơ sở dữ liệu
            Consumer<T> setParentConsumer, // Hàm thiết lập quan hệ ngược
            Function<T, T> saveEntityFunction // Hàm lưu entity
    ) {
        List<T> updatedEntities = new ArrayList<>();
        for (T entity : entities) {
            if (entity != null) {
                // Lấy ID của entity
                ID entityId = idExtractor.apply(entity);
                if (entityId != null) {
                    // Tìm entity trong cơ sở dữ liệu
                    T existingEntity = findEntityFunction.apply(entityId);
                    if (existingEntity == null) {
                        throw new IllegalArgumentException("Entity with ID " + entityId + " does not exist.");
                    }
                    // Cập nhật ngược quan hệ
                    setParentConsumer.accept(existingEntity);
                    updatedEntities.add(existingEntity);
                } else {
                    // Tạo mới entity
                    setParentConsumer.accept(entity);
                    T newEntity = saveEntityFunction.apply(entity);
                    updatedEntities.add(newEntity);
                }
            }
        }
        return updatedEntities;
    }
    // MANY TO MANY COMPLEX
    public <T, U> List<T> processNestedEntityList_MTMP(
            EntityManager entityManager,
            List<T> entities,
            Class<T> entityClass,
            Function<T, Object> getParentIdFunction,
            Function<T, U> getNestedEntityFunction,
            BiConsumer<T, U> setNestedEntityFunction,
            Class<U> nestedEntityClass,
            Function<U, Object> getNestedEntityIdFunction,
            String parentErrorPrefix,
            String nestedErrorPrefix
    ) {
        List<T> processedList = new ArrayList<>();
        for (T entity : entities) {
            if (entity != null) {
                Object parentId = getParentIdFunction.apply(entity);
                if (parentId != null) {
                    T existingEntity = entityManager.find(entityClass, parentId);
                    if (existingEntity == null) {
                        throw new IllegalArgumentException(parentErrorPrefix + parentId + " does not exist.");
                    }

                    U nestedEntity = getNestedEntityFunction.apply(existingEntity);
                    if (nestedEntity != null) {
                        Object nestedEntityId = getNestedEntityIdFunction.apply(nestedEntity);
                        if (nestedEntityId != null) {
                            U existingNestedEntity = entityManager.find(nestedEntityClass, nestedEntityId);
                            if (existingNestedEntity == null) {
                                throw new IllegalArgumentException(nestedErrorPrefix + nestedEntityId + " does not exist.");
                            }
                            setNestedEntityFunction.accept(existingEntity, existingNestedEntity);
                        }
                    }

                    processedList.add(existingEntity);
                } else {
                    U nestedEntity = getNestedEntityFunction.apply(entity);
                    if (nestedEntity != null) {
                        Object nestedEntityId = getNestedEntityIdFunction.apply(nestedEntity);
                        if (nestedEntityId != null) {
                            U existingNestedEntity = entityManager.find(nestedEntityClass, nestedEntityId);
                            if (existingNestedEntity == null) {
                                throw new IllegalArgumentException(nestedErrorPrefix + nestedEntityId + " does not exist.");
                            }
                            setNestedEntityFunction.accept(entity, existingNestedEntity);
                        }
                    }

                    T newEntity = entityManager.merge(entity);
                    processedList.add(newEntity);
                }
            }
        }
        return processedList;
    }




}
