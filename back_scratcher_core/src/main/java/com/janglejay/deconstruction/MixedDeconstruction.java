package com.janglejay.deconstruction;

import com.janglejay.enums.SentenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MixedDeconstruction extends Deconstruction{

    SentenceTypeEnum sentenceTypeEnum;

    MockerDeconstruction mockerDeconstruction;

    DoReturnDeconstruction doReturnDeconstruction;

}
