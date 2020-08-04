import json
import random
import sys
from pymongo import MongoClient


def insert_questions():
    client = MongoClient()
    db = client["question-database"]
    question_collection = db["questions"]
    with open("initial_data_load.json", 'r') as file:
        questions = json.load(file)
        data = {"moduleId": "1", "questions":questions}
        question_collection.delete_many({})
        question_collection.insert_one(data)


if __name__ == "__main__":

    insert_questions()
