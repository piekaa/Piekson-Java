package pl.piekoszek.storage.mongo;

import pl.piekoszek.collections.ByteReader;
import pl.piekoszek.collections.ByteBuffer;

class MongoHeader {
    int messageLength;
    int requestId;
    int responseTo;
    int opCode;

    byte[] bytes() {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.addLittleEndian(messageLength);
        byteBuffer.addLittleEndian(requestId);
        byteBuffer.addLittleEndian(responseTo);
        byteBuffer.addLittleEndian(opCode);
        return byteBuffer.getAllBytes();
    }

    MongoHeader() {
    }

    MongoHeader(ByteReader byteReader) {
        messageLength = byteReader.readInt();
        requestId = byteReader.readInt();
        responseTo = byteReader.readInt();
        opCode = byteReader.readInt();
    }
}
