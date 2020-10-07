package com.gosty.repository

import com.gosty.model.Question

interface SupportRepo {

    suspend fun getQuestions(): List<Question>

}

class SupportRepoImpl : SupportRepo {

    override suspend fun getQuestions(): List<Question> {
        //todo replace with api call
        return listOf(
            Question(
                1,
                "How would I open the front door?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                2,
                "How would I open the apartment door?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                3,
                "Where could I address my requests in case of issues with accessing the apartment or other questions related to my stay?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                4,
                "Since which moment will I get access to the apartment to stay?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                5,
                "What are the restrictions during my stay in the chosen apartment?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                6,
                "Which are your requirements to guests?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            ),
            Question(
                7,
                "Which are your requirements to guests?",
                "We always send guidelines to your email, but you will also find the button “open the door” in your mobile application. If you push the button “open the door”, an explanation will flash on how to open the door using the code that you will need to dial on the intercom keyboard."
            )
        )
    }

}