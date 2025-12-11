package com.example.dietService.service;

import com.example.dietService.dto.AiResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AiService {

    public Optional<AiResponseDto> getNutritionalDataFromAi(String food,String quantity)
    {
        Client client = Client.builder().apiKey("AIzaSyDO4ZxcWAHkvNENPnbUKrOqwK9Uibucti8").build();
        Schema servingSizeSchema = Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(Map.of(
                        "value", Schema.builder()
                                .type(Type.Known.NUMBER)
                                .build(),
                        "unit", Schema.builder()
                                .type(Type.Known.STRING)
                                .build()
                ))
                .required(List.of("value", "unit"))
                .build();

        Schema macrosSchema = Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(Map.of(
                        "calories", Schema.builder().type(Type.Known.NUMBER).build(),
                        "protein_g", Schema.builder().type(Type.Known.NUMBER).build(),
                        "carbs_g", Schema.builder().type(Type.Known.NUMBER).build(),
                        "fat_g", Schema.builder().type(Type.Known.NUMBER).build(),
                        "fiber_g", Schema.builder().type(Type.Known.NUMBER).build(),
                        "sugar_g", Schema.builder().type(Type.Known.NUMBER).build()
                ))
                .required(List.of("calories", "protein_g", "carbs_g", "fat_g"))
                .build();

        Schema finalSchema = Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(Map.of(
                        "food_name", Schema.builder().type(Type.Known.STRING).build(),
                        "input_query", Schema.builder().type(Type.Known.STRING).build(),
                        "macros", macrosSchema,
                        "serving", servingSizeSchema
                ))
                .required(List.of("food_name", "input_query", "macros","serving"))
                .build();

        GenerateContentConfig config =
                GenerateContentConfig.builder()
                        .systemInstruction(
                                Content.fromParts(Part.fromText("You are nutritionist")))

                        // Disables thinking
                        .thinkingConfig(ThinkingConfig.builder().thinkingBudget(0).build())
                        .responseMimeType("application/json")
                        .responseSchema(finalSchema)
                        .build();

        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", "tell me macros for "+food +" "+quantity+"gm", config);


        ObjectMapper objectMapper = new ObjectMapper();
        AiResponseDto aiResponseDto = null;

        try {
           aiResponseDto =  objectMapper.readValue(response.text(), AiResponseDto.class);
        } catch (IOException e) {
            log.error("Error parsing Ai response");
            log.info(e.getStackTrace().toString());
        }
        System.out.println(response);

        return Optional.ofNullable(aiResponseDto);

    }
}
