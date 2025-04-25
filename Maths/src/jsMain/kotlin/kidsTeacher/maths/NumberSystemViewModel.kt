package kidsTeacher.maths

import kidsTeacher.maths.data.NumberDictionary
import kotlinx.serialization.json.Json
import logger.jsLog

val numberData: String = """
{"numbers":{
  "1": { "en": "one", "hi": "एक" },
  "2": { "en": "two", "hi": "दो" },
  "3": { "en": "three", "hi": "तीन" },
  "4": { "en": "four", "hi": "चार" },
  "5": { "en": "five", "hi": "पांच" },
  "6": { "en": "six", "hi": "छह" },
  "7": { "en": "seven", "hi": "सात" },
  "8": { "en": "eight", "hi": "आठ" },
  "9": { "en": "nine", "hi": "नौ" },
  "10": { "en": "ten", "hi": "दस" },
  "11": { "en": "eleven", "hi": "ग्यारह" },
  "12": { "en": "twelve", "hi": "बारह" },
  "13": { "en": "thirteen", "hi": "तेरह" },
  "14": { "en": "fourteen", "hi": "चौदह" },
  "15": { "en": "fifteen", "hi": "पंद्रह" },
  "16": { "en": "sixteen", "hi": "सोलह" },
  "17": { "en": "seventeen", "hi": "सत्रह" },
  "18": { "en": "eighteen", "hi": "अठारह" },
  "19": { "en": "nineteen", "hi": "उन्नीस" },
  "20": { "en": "twenty", "hi": "बीस" }
}
}
"""

class NumberSystemViewModel {

    val data = Json.decodeFromString<NumberDictionary>(numberData)

    init {
        jsLog("KidsTeacherViewModel initialized")
    }
}
