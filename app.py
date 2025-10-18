from fastapi import FastAPI, Request
from sentence_transformers import SentenceTransformer
from pydantic import BaseModel

app = FastAPI()
model = SentenceTransformer("all-MiniLM-L6-v2")

class TextRequest(BaseModel):
    text: str

@app.post("/embed")
async def embed(request: TextRequest):
    embedding = model.encode(request.text).tolist()
    return {"embedding": embedding}
