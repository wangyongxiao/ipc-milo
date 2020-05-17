package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.CubeItemType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class CubeItemTypeNode extends ArrayItemTypeNode implements CubeItemType {
    public CubeItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                            UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                            UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                            double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public AxisInformation getXAxisDefinition() throws UaException {
        PropertyTypeNode node = getXAxisDefinitionNode();
        return (AxisInformation) node.getValue().getValue().getValue();
    }

    @Override
    public void setXAxisDefinition(AxisInformation xAxisDefinition) throws UaException {
        PropertyTypeNode node = getXAxisDefinitionNode();
        node.setValue(new Variant(xAxisDefinition));
    }

    @Override
    public AxisInformation readXAxisDefinition() throws UaException {
        try {
            return readXAxisDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeXAxisDefinition(AxisInformation xAxisDefinition) throws UaException {
        try {
            writeXAxisDefinitionAsync(xAxisDefinition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisInformation> readXAxisDefinitionAsync() {
        return getXAxisDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (AxisInformation) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeXAxisDefinitionAsync(AxisInformation xAxisDefinition) {
        DataValue value = DataValue.valueOnly(new Variant(xAxisDefinition));
        return getXAxisDefinitionNodeAsync()
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
    public PropertyTypeNode getXAxisDefinitionNode() throws UaException {
        try {
            return getXAxisDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getXAxisDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "XAxisDefinition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public AxisInformation getYAxisDefinition() throws UaException {
        PropertyTypeNode node = getYAxisDefinitionNode();
        return (AxisInformation) node.getValue().getValue().getValue();
    }

    @Override
    public void setYAxisDefinition(AxisInformation yAxisDefinition) throws UaException {
        PropertyTypeNode node = getYAxisDefinitionNode();
        node.setValue(new Variant(yAxisDefinition));
    }

    @Override
    public AxisInformation readYAxisDefinition() throws UaException {
        try {
            return readYAxisDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeYAxisDefinition(AxisInformation yAxisDefinition) throws UaException {
        try {
            writeYAxisDefinitionAsync(yAxisDefinition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisInformation> readYAxisDefinitionAsync() {
        return getYAxisDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (AxisInformation) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeYAxisDefinitionAsync(AxisInformation yAxisDefinition) {
        DataValue value = DataValue.valueOnly(new Variant(yAxisDefinition));
        return getYAxisDefinitionNodeAsync()
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
    public PropertyTypeNode getYAxisDefinitionNode() throws UaException {
        try {
            return getYAxisDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getYAxisDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "YAxisDefinition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public AxisInformation getZAxisDefinition() throws UaException {
        PropertyTypeNode node = getZAxisDefinitionNode();
        return (AxisInformation) node.getValue().getValue().getValue();
    }

    @Override
    public void setZAxisDefinition(AxisInformation zAxisDefinition) throws UaException {
        PropertyTypeNode node = getZAxisDefinitionNode();
        node.setValue(new Variant(zAxisDefinition));
    }

    @Override
    public AxisInformation readZAxisDefinition() throws UaException {
        try {
            return readZAxisDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeZAxisDefinition(AxisInformation zAxisDefinition) throws UaException {
        try {
            writeZAxisDefinitionAsync(zAxisDefinition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisInformation> readZAxisDefinitionAsync() {
        return getZAxisDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (AxisInformation) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeZAxisDefinitionAsync(AxisInformation zAxisDefinition) {
        DataValue value = DataValue.valueOnly(new Variant(zAxisDefinition));
        return getZAxisDefinitionNodeAsync()
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
    public PropertyTypeNode getZAxisDefinitionNode() throws UaException {
        try {
            return getZAxisDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getZAxisDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ZAxisDefinition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
