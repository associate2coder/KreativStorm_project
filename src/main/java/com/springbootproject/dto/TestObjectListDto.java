package com.springbootproject.dto;

import com.springbootproject.object.TestObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestObjectListDto {
    private List<TestObject> testObjectList;

    public void addTestObjectToList(TestObject testObject) {
        testObjectList.add(testObject);
    }
}
