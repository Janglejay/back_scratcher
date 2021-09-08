package com.janglejay.deconstruction;

import com.janglejay.enums.MockerTypeEnum;
import lombok.Data;

@Data
public class MockerDeconstruction extends Deconstruction{
    //    String className = list.get(0);
//    String valName = list.get(1);
//    String line1 = className + " " + valName + " = " + "new ArrayList<>();";
//    String innerClassName = className.substring(className.indexOf("<") + 1, className.indexOf(">"));
//    String innerValName = CaseFormat.LOWER_CAMEL.to(LOWER_CAMEL, innerClassName);
//    String line2 = mockNormal(List.of(innerClassName, innerValName));
//    String line3 = valName + ".add(" + innerValName + ");";
    private final MockerTypeEnum mockerType;
    private final String className;
    private final String valName;
    private final String innerClassName;
    private final String innerValName;

    private MockerDeconstruction(Builder builder) {
        this.mockerType = builder.mockerType;
        this.className = builder.className;
        this.valName = builder.valName;
        this.innerClassName = builder.innerClassName;
        this.innerValName = builder.innerValName;
    }

    public static class Builder {
        private MockerTypeEnum mockerType;
        private String className;
        private String valName;
        private String innerClassName;
        private String innerValName;

        public Builder() {
        }

        public Builder setMockerType(MockerTypeEnum mockerType) {
            this.mockerType = mockerType;
            return this;
        }

        public Builder setClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder setValName(String valName) {
            this.valName = valName;
            return this;
        }

        public Builder setInnerClassName(String innerClassName) {
            this.innerClassName = innerClassName;
            return this;
        }

        public Builder setInnerValName(String innerValName) {
            this.innerValName = innerValName;
            return this;
        }

        public MockerDeconstruction build() {
            return new MockerDeconstruction(this);
        }
    }
}
