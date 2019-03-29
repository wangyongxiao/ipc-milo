/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.util.UUID;
import java.util.function.BiConsumer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.util.DocumentBuilderUtil;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OpcUaXmlStreamEncoder implements UaEncoder {

    private final DocumentBuilder builder;

    private Document document;
    private Node currentNode;

    private final SerializationContext context;

    public OpcUaXmlStreamEncoder(SerializationContext context) {
        this.context = context;

        try {
            builder = DocumentBuilderUtil.SHARED_FACTORY.newDocumentBuilder();

            document = builder.newDocument();
            currentNode = document;
        } catch (ParserConfigurationException e) {
            throw new UaRuntimeException(StatusCodes.Bad_InternalError, e);
        }
    }

    public Document getDocument() {
        return document;
    }

    public String getDocumentXml() {
        return "";
    }

    @Override
    public void writeBoolean(String field, Boolean value) throws UaSerializationException {
        Node element = document.createElementNS(Namespaces.OPC_UA_XSD, field);

        element.appendChild(document.createTextNode(value.toString()));

        currentNode.appendChild(element);
    }

    @Override
    public void writeSByte(String field, Byte value) throws UaSerializationException {

    }

    @Override
    public void writeInt16(String field, Short value) throws UaSerializationException {

    }

    @Override
    public void writeInt32(String field, Integer value) throws UaSerializationException {

    }

    @Override
    public void writeInt64(String field, Long value) throws UaSerializationException {

    }

    @Override
    public void writeByte(String field, UByte value) throws UaSerializationException {

    }

    @Override
    public void writeUInt16(String field, UShort value) throws UaSerializationException {

    }

    @Override
    public void writeUInt32(String field, UInteger value) throws UaSerializationException {

    }

    @Override
    public void writeUInt64(String field, ULong value) throws UaSerializationException {

    }

    @Override
    public void writeFloat(String field, Float value) throws UaSerializationException {

    }

    @Override
    public void writeDouble(String field, Double value) throws UaSerializationException {

    }

    @Override
    public void writeString(String field, String value) throws UaSerializationException {

    }

    @Override
    public void writeDateTime(String field, DateTime value) throws UaSerializationException {

    }

    @Override
    public void writeGuid(String field, UUID value) throws UaSerializationException {

    }

    @Override
    public void writeByteString(String field, ByteString value) throws UaSerializationException {

    }

    @Override
    public void writeXmlElement(String field, XmlElement value) throws UaSerializationException {

    }

    @Override
    public void writeNodeId(String field, NodeId value) throws UaSerializationException {

    }

    @Override
    public void writeExpandedNodeId(String field, ExpandedNodeId value) throws UaSerializationException {

    }

    @Override
    public void writeStatusCode(String field, StatusCode value) throws UaSerializationException {

    }

    @Override
    public void writeQualifiedName(String field, QualifiedName value) throws UaSerializationException {

    }

    @Override
    public void writeLocalizedText(String field, LocalizedText value) throws UaSerializationException {

    }

    @Override
    public void writeExtensionObject(String field, ExtensionObject value) throws UaSerializationException {

    }

    @Override
    public void writeDataValue(String field, DataValue value) throws UaSerializationException {

    }

    @Override
    public void writeVariant(String field, Variant value) throws UaSerializationException {

    }

    @Override
    public void writeDiagnosticInfo(String field, DiagnosticInfo value) throws UaSerializationException {

    }

    @Override
    public <T> void writeArray(
        String field,
        T[] values,
        BiConsumer<String, T> encoder) throws UaSerializationException {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends UaStructure> void writeBuiltinStruct(
        String field,
        T value,
        Class<T> clazz) throws UaSerializationException {

        Node node = currentNode;

        BuiltinDataTypeCodec<T> codec = (BuiltinDataTypeCodec<T>) BuiltinDataTypeDictionary.getBuiltinCodec(clazz);

        if (codec == null) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, "no codec found: " + clazz);
        }

        currentNode = document.createElementNS(Namespaces.OPC_UA_XSD, field);
        codec.encode(context, value, this);

        currentNode = node;
    }

    @Override
    public <T extends UaStructure> void writeBuiltinStructArray(
        String field,
        T[] values,
        Class<T> clazz) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, NodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeStructArray(String field, Object[] value, NodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeMessage(String field, UaMessage message) throws UaSerializationException {

    }

}
