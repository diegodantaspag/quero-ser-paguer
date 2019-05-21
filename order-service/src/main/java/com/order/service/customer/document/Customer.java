package com.order.service.customer.document;

import com.google.common.base.CharMatcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.util.StringUtils.isEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Customer")
@TypeAlias(value = "Customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String document;
    private LocalDate birthDate;

    public String getDocument() {
        return !isEmpty(this.document) ? CharMatcher.JAVA_DIGIT.retainFrom(this.document) : null;
    }

    public void setId(String id) {
        this.id = id;
    }
}