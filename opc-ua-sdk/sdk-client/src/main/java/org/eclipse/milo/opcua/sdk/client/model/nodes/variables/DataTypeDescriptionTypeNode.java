package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataTypeDescriptionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class DataTypeDescriptionTypeNode extends BaseDataVariableTypeNode implements DataTypeDescriptionType {
    public DataTypeDescriptionTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                       UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                       double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public String getDataTypeVersion() throws UaException {
        PropertyTypeNode node = getDataTypeVersionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setDataTypeVersion(String dataTypeVersion) throws UaException {
        PropertyTypeNode node = getDataTypeVersionNode();
        node.setValue(new Variant(dataTypeVersion));
    }

    @Override
    public String readDataTypeVersion() throws UaException {
        try {
            return readDataTypeVersionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDataTypeVersion(String dataTypeVersion) throws UaException {
        try {
            writeDataTypeVersionAsync(dataTypeVersion).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readDataTypeVersionAsync() {
        return getDataTypeVersionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDataTypeVersionAsync(String dataTypeVersion) {
        DataValue value = DataValue.valueOnly(new Variant(dataTypeVersion));
        return getDataTypeVersionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getDataTypeVersionNode() throws UaException {
        try {
            return getDataTypeVersionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDataTypeVersionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DataTypeVersion", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public ByteString getDictionaryFragment() throws UaException {
        PropertyTypeNode node = getDictionaryFragmentNode();
        return (ByteString) node.getValue().getValue().getValue();
    }

    @Override
    public void setDictionaryFragment(ByteString dictionaryFragment) throws UaException {
        PropertyTypeNode node = getDictionaryFragmentNode();
        node.setValue(new Variant(dictionaryFragment));
    }

    @Override
    public ByteString readDictionaryFragment() throws UaException {
        try {
            return readDictionaryFragmentAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDictionaryFragment(ByteString dictionaryFragment) throws UaException {
        try {
            writeDictionaryFragmentAsync(dictionaryFragment).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ByteString> readDictionaryFragmentAsync() {
        return getDictionaryFragmentNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ByteString) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDictionaryFragmentAsync(ByteString dictionaryFragment) {
        DataValue value = DataValue.valueOnly(new Variant(dictionaryFragment));
        return getDictionaryFragmentNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getDictionaryFragmentNode() throws UaException {
        try {
            return getDictionaryFragmentNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDictionaryFragmentNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DictionaryFragment", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
