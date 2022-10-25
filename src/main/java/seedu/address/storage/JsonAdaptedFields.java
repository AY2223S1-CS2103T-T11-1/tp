package seedu.address.storage;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.person.Fields;

public class JsonAdaptedFields {

    List<JsonAdaptedField> fields;

    public JsonAdaptedFields(Fields source) {
        fields = source.toList().stream().map(JsonAdaptedField::new).collect(Collectors.toList());
    }
}
